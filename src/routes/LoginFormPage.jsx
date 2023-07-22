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
      const credentials = {
        username: username,
        password: password,
      };
  
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials),
      });
  
      if (response.ok) {
        const result = await response.json();
  
        if (result.token !== null) {
          const expirationTime = new Date();
          expirationTime.setTime(expirationTime.getTime() + 1 * 60 * 60 * 1000); // 1 hour
          document.cookie = `token=${result.token}; expires=${expirationTime.toUTCString()}; path=/`;
          
          alert('Logged on');
        } else {
          alert('Username or password is incorrect');
        }
      } else {
        const fetchedData = await response.json();
        alert(fetchedData.errorMessage);
      }
    } catch (error) {
      alert('Login failed', error);
    }
  };
  

  return (
    <div className="FormContainer w-1/3">
      <h1 className="FormTitle">Login</h1>
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


/*
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
      const formData = new FormData();
      formData.append('username', username);
      formData.append('password', password);

      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        body: formData,
      });

      if (response.ok) {
        const result = await response.json();

        if (result.token !== null) {
          alert('Logged on');
        } else {
          alert('Username or password is incorrect');
        }
      } else {
        const fetchedData = await response.json();
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
*/