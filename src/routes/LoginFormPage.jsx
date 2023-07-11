import { useState } from 'react';
import { Link } from 'react-router-dom';

const LoginFormPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username,
          password,
        }),
      });

      if (response.ok) {
        alert('Logged on');
      } else {
        let fetchedData = await response.json();
        alert(fetchedData.errorMessage);
      }
    } catch (error) {
      alert('Login failed', error);
    }
  };

  return (
    <div className="FormContainer w-1/3">
      <h1 className="FormTitle">Client Login</h1>
      <form onSubmit={handleSubmit}>
        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Username</label>
          </div>
          <input
            className="Input"
            type="text"
            value={username}
            onChange={handleUsernameChange}
            required
            maxLength={50}
          />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Password</label>
          </div>
          <input
            className="Input"
            type="password"
            value={password}
            onChange={handlePasswordChange}
            required
            minLength={6}
          />
        </div>

        <button
          type="submit"
          className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded"
          style={{ marginTop: 10 }}
        >
          Login
        </button>
      </form>

      <div className="NotAMember">
        Not a member?{' '}
        <Link to="/RegistrationForm">Register here</Link>
      </div>
    </div>
  );
};

export default LoginFormPage;
