import React, { useState } from 'react';

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
        <div>
            <h1>Ticket Management System</h1>
            <form>
                <label>
                    Max Ticket Capacity:
                    <input
                        type="number"
                        name="maxTicketCapacity"
                        value={config.maxTicketCapacity}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Total Tickets:
                    <input
                        type="number"
                        name="totalTickets"
                        value={config.totalTickets}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Ticket Release Rate:
                    <input
                        type="number"
                        name="ticketReleaseRate"
                        value={config.ticketReleaseRate}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Ticket Retrieval Rate:
                    <input
                        type="number"
                        name="customerRetrievalRate"
                        value={config.customerRetrievalRate}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <button type="button" onClick={setConfiguration}>
                    Set Configuration
                </button>
            </form>
            <button onClick={startSystem}>Start System</button>
            <button onClick={stopSystem}>Stop System</button>
        </div>
    );
};

export default App;
