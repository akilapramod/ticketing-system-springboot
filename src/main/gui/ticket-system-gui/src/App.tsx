import React, { useState } from 'react';

// Configuration Section Component
const ConfigurationSection = ({ config, setConfig, onSetConfiguration }) => {
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setConfig({ ...config, [e.target.name]: e.target.value });
  };

  return (
    <div className="space-y-6">
      <div className="grid gap-6">
        {[
          {
            name: 'maxTicketCapacity',
            label: 'Max Ticket Capacity'
          },
          {
            name: 'totalTickets',
            label: 'Total Tickets'
          },
          {
            name: 'ticketReleaseRate',
            label: 'Ticket Release Rate'
          },
          {
            name: 'customerRetrievalRate',
            label: 'Ticket Retrieval Rate'
          }
        ].map(({ name, label }) => (
          <div key={name} className="flex flex-col">
            <label className="text-sm font-medium text-blue-700 mb-1">
              {label}:
              <input
                type="number"
                name={name}
                value={config[name]}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border-blue-300 shadow-sm focus:border-blue-500 focus:ring-blue-500 bg-white text-blue-900"
              />
            </label>
          </div>
        ))}
      </div>
      <button
        type="button"
        onClick={onSetConfiguration}
        className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-colors duration-200"
      >
        Set Configuration
      </button>
    </div>
  );
};

// Control Panel Section Component
const ControlPanelSection = ({ onStartSystem, onStopSystem }) => {
  return (
    <div className="flex space-x-4">
      <button
        onClick={onStartSystem}
        className="flex-1 bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-700 transition-colors duration-200"
      >
        Start System
      </button>
      <button
        onClick={onStopSystem}
        className="flex-1 bg-red-600 text-white py-2 px-4 rounded-md hover:bg-red-700 transition-colors duration-200"
      >
        Stop System
      </button>
    </div>
  );
};

// Ticket Status Section Component
const TicketStatusSection = ({ availableTickets }:{availableTickets:number}) => {
  return (
    <div className="bg-gray-100 p-6 rounded-lg">
      <h3 className="text-xl font-semibold text-blue-800 mb-4">
        Ticket Pool Status
      </h3>
      <div className="flex justify-between items-center">
        <span className="text-lg text-blue-700">Available Tickets:</span>
        <span className="text-2xl font-bold text-green-600">
          {availableTickets}
        </span>
      </div>
    </div>
  );
};

// Main App Component
const App: React.FC = () => {
  const [activeSection, setActiveSection] = useState<'configuration' | 'controlPanel' | 'ticketStatus'>('configuration');
  const [config, setConfig] = useState({
    maxTicketCapacity: '',
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
  });
  const [availableTickets, setAvailableTickets] = useState(0);

  const setConfiguration = async () => {
    try {
      const response = await fetch('/api/configuration/set', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(config),
      });

      if (response.ok) {
        // Assuming the response returns the initial available tickets
        const result = await response.json();
        setAvailableTickets(result.availableTickets);
      }
    } catch (error) {
      console.error('Error setting configuration:', error);
    }
  };

  const startSystem = async () => {
    try {
      const response = await fetch('/api/configuration/start', { method: 'POST' });
      if (response.ok) {
        const result = await response.json();
        setAvailableTickets(result.availableTickets);
      }
    } catch (error) {
      console.error('Error starting system:', error);
    }
  };

  const stopSystem = async () => {
    try {
      await fetch('/api/configuration/stop', { method: 'POST' });
      setAvailableTickets(0);
    } catch (error) {
      console.error('Error stopping system:', error);
    }
  };





  return (
    <div className="min-h-screen bg-blue-100 py-8 px-4">
      <div className="max-w-2xl mx-auto bg-white rounded-lg shadow-lg p-8">
        <h1 className="text-3xl font-bold text-center mb-8 text-blue-800">
          Ticket Management System
        </h1>

        {/* Section Navigation */}
        <div className="flex mb-6 space-x-4 justify-center">
          {[
            {
              id: 'configuration',
              label: 'Configuration',
              icon: '⚙️'
            },
            {
              id: 'controlPanel',
              label: 'Control Panel',
              icon: '🎛️'
            },
            {
              id: 'ticketStatus',
              label: 'Ticket Status',
              icon: '🎫'
            }
          ].map(({ id, label, icon }) => (
            <button
              key={id}
              onClick={() => setActiveSection(id as any)}
              className={`
                px-4 py-2 rounded-md transition-colors duration-200
                ${activeSection === id 
                  ? 'bg-blue-600 text-white' 
                  : 'bg-blue-100 text-blue-700 hover:bg-blue-200'
                }
              `}
            >
              {icon} {label}
            </button>
          ))}
        </div>

        {/* Conditional Rendering of Sections */}
        {activeSection === 'configuration' && (
          <ConfigurationSection
            config={config}
            setConfig={setConfig}
            onSetConfiguration={setConfiguration}
          />
        )}

        {activeSection === 'controlPanel' && (
          <ControlPanelSection
            onStartSystem={startSystem}
            onStopSystem={stopSystem}
          />
        )}

        {activeSection === 'ticketStatus' && (
          <TicketStatusSection
            availableTickets={availableTickets}
          />
        )}
      </div>
    </div>
  );
};

export default App;