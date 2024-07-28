import React, { useEffect, useState } from 'react';
import axios from 'axios';

const FlightStatus = () => {
  const [flightData, setFlightData] = useState([]);
  const [flightNumber, setFlightNumber] = useState('');
  const [notificationMethod, setNotificationMethod] = useState('email');

  useEffect(() => {
    const fetchFlightData = async () => {
      const response = await axios.get('/api/flights');
      setFlightData(response.data);
    };
    fetchFlightData();
  }, []);

  const handleSubscribe = async () => {
    await axios.post('/api/subscribe', { flightNumber, notificationMethod });
    alert('Subscribed to notifications');
  };

  return (
    <div>
      <h1>Flight Status</h1>
      <ul>
        {flightData.map(flight => (
          <li key={flight.flightNumber}>{flight.flightNumber}: {flight.status}</li>
        ))}
      </ul>
      <div>
        <input
          type="text"
          placeholder="Flight Number"
          value={flightNumber}
          onChange={e => setFlightNumber(e.target.value)}
        />
        <select onChange={e => setNotificationMethod(e.target.value)}>
          <option value="email">Email</option>
          <option value="sms">SMS</option>
          <option value="app">App Notification</option>
        </select>
        <button onClick={handleSubscribe}>Subscribe</button>
      </div>
    </div>
  );
};

export default FlightStatus;
