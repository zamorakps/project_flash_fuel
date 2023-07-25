import { useState, useEffect } from 'react';
import '../styles/Main.css';

const ProfileManagementPage = () => {
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [addressLine2, setAddressLine2] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [zipCode, setZipCode] = useState('');
  useEffect(() => {
    const cookies = document.cookie.split(';').reduce((cookiesObject, cookie) => {
      const [name, value] = cookie.trim().split('=');
      cookiesObject[name] = value;
      return cookiesObject;
    }, {});
    const bearerToken = cookies.token;

    if (!bearerToken) {
      console.error('Token not found in cookies. Please log in first.');
      return;
    }

    fetchProfileData(bearerToken);
  }, []);

  const fetchProfileData = async (bearerToken) => {
    try {
      const response = await fetch(`http://localhost:8080/api/profile`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${bearerToken}`,
        },
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const profileData = await response.json();
      setName(profileData.name || '');
      setAddress(profileData.address || '');
      setAddressLine2(profileData.addressLine2 || '');
      setCity(profileData.city || '');
      setState(profileData.state || '');
      setZipCode(profileData.zipCode || '');
    } catch (error) {
      console.error('Failed to fetch profile data:', error);
    }
  };
  

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    const zipCodeValidation = new RegExp("^(F)?\\d{5,}$");
  
    if (zipCode.length < 5 || !zipCodeValidation.test(zipCode)) {
      alert("Zip code must be at least 5 digits long");
    } else {
      try {
        // Get the token from the cookies
        const cookies = document.cookie.split(';').reduce((cookiesObject, cookie) => {
          const [name, value] = cookie.trim().split('=');
          cookiesObject[name] = value;
          return cookiesObject;
        }, {});
        const bearerToken = cookies.token;
  
        if (!bearerToken) {
          console.error('Token not found in cookies. Please log in first.');
          return;
        }
  
        const profileData = {
          name,
          address,
          addressLine2,
          city,
          state,
          zipCode,
        };
  
        const response = await fetch(`http://localhost:8080/api/profile/update`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${bearerToken}`, // Include the bearer token in the headers
          },
          body: JSON.stringify(profileData),
        });
  
        console.log(response.json());
        if (response.ok) {
          alert('Profile update successful');
        } else {
          const errorData = await response.json();
          alert('Profile update failed:', errorData.message);
        }
      } catch (error) {
        alert('Profile update failed:', error);
      }
    }
  };
  
  return (
    <div className="FormContainer w-1/3 mb-6">
      <h1 className="FormTitle">Profile Management</h1>
      <form onSubmit={handleSubmit}>
        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Name</label>
          </div>
          <input className="Input" type="text" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Address</label>
          </div>
          <input className="Input" type="text" value={address} onChange={(e) => setAddress(e.target.value)} required />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Address Line 2</label>
          </div>
          <input className="Input" type="text" value={addressLine2} onChange={(e) => setAddressLine2(e.target.value)} />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">City</label>
          </div>
          <input className="Input" type="text" value={city} onChange={(e) => setCity(e.target.value)} required />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">State</label>
          </div>
          <input className="Input" type="text" value={state} onChange={(e) => setState(e.target.value)} required />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Zip Code</label>
          </div>
          <input className="Input" type="text" value={zipCode} id='ZipCodeInput' onChange={(e) => setZipCode(e.target.value)} required />
        </div>

        <button type="submit" className="border-solid shadow w-full cursor-pointer text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default ProfileManagementPage;