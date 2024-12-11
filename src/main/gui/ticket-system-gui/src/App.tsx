import React, { useState } from 'react';
import './App.css';

const App: React.FC = () => {
    const [config, setConfig] = useState({
        maxTicketCapacity: '',
        totalTickets: '',
        ticketReleaseRate: '',
        customerRetrievalRate: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setConfig({ ...config, [e.target.name]: e.target.value });
    };

    const setConfiguration = async () => {
        await fetch('/api/configuration/set', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(config),
        });
    };

    const startSystem = async () => {
        await fetch('/api/configuration/start', { method: 'POST' });
    };

    const stopSystem = async () => {
        await fetch('/api/configuration/stop', { method: 'POST' });
    };

    return (
        <div className="min-h-screen bg-blue-100 py-8 px-4">
            <div className="max-w-2xl mx-auto bg-white rounded-lg shadow-lg p-8 mb-6">
                <h1 className="text-3xl font-bold text-center mb-8 text-blue-800">Ticket Management System</h1>
                <form className="space-y-6 mb-8">
                    <div className="grid gap-6">
                        <div className="flex flex-col">
                            <label className="text-sm font-medium text-blue-700 mb-1">
                                Max Ticket Capacity:
                                <input
                                    type="number"
                                    name="maxTicketCapacity"
                                    value={config.maxTicketCapacity}
                                    onChange={handleChange}
                                    className="mt-1 block w-full rounded-md border-blue-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 bg-white text-blue-900"
                                />
                            </label>
                        </div>
                        <div className="flex flex-col">
                            <label className="text-sm font-medium text-blue-700 mb-1">
                                Total Tickets:
                                <input
                                    type="number"
                                    name="totalTickets"
                                    value={config.totalTickets}
                                    onChange={handleChange}
                                    className="mt-1 block w-full rounded-md border-blue-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 bg-white text-blue-900"
                                />
                            </label>
                        </div>
                        <div className="flex flex-col">
                            <label className="text-sm font-medium text-blue-700 mb-1">
                                Ticket Release Rate:
                                <input
                                    type="number"
                                    name="ticketReleaseRate"
                                    value={config.ticketReleaseRate}
                                    onChange={handleChange}
                                    className="mt-1 block w-full rounded-md border-blue-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 bg-white text-blue-900"
                                />
                            </label>
                        </div>
                        <div className="flex flex-col">
                            <label className="text-sm font-medium text-blue-700 mb-1">
                                Ticket Retrieval Rate:
                                <input
                                    type="number"
                                    name="customerRetrievalRate"
                                    value={config.customerRetrievalRate}
                                    onChange={handleChange}
                                    className="mt-1 block w-full rounded-md border-blue-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 bg-white text-blue-900"
                                />
                            </label>
                        </div>
                    </div>
                    <button
                        type="button"
                        onClick={setConfiguration}
                        className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-colors duration-200"
                    >
                        Set Configuration
                    </button>
                </form>
                <div className="flex space-x-4">
                    <button
                        onClick={startSystem}
                        className="flex-1 bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-700 transition-colors duration-200"
                    >
                        Start System
                    </button>
                    <button
                        onClick={stopSystem}
                        className="flex-1 bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 transition-colors duration-200"
                    >
                        Stop System
                    </button>
                </div>
            </div>
            <div className="max-w-2xl mx-auto bg-white rounded-lg shadow-lg p-8">
                <h4 className="text-xl font-semibold text-blue-800 mb-4">System Logs</h4>
                <form className="bg-gray-50 p-4 rounded-md">

                </form>
            </div>
        </div>
    );
};

export default App;
