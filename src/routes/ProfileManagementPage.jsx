import { useState, useEffect } from 'react';

const ProfileManagementPage = () => {
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [addressLine2, setAddressLine2] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [zipCode, setZipCode] = useState('');

  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/profile?username=admin');
        if (response.ok) {
          const profileData = await response.json();
          if (profileData) {
            setName(profileData.name || '');
            setAddress(profileData.address || '');
            setAddressLine2(profileData.addressLine2 || '');
            setCity(profileData.city || '');
            setState(profileData.state || '');
            setZipCode(profileData.zipCode || '');
          }
        } else {
          alert('Failed to fetch profile data:', response.status);
        }
      } catch (error) {
        alert('Failed to fetch profile data:', error);
      }
    };

    fetchProfileData();
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      let formData = new FormData();

      formData.append('username', name);
      formData.append('name', address);
      formData.append('address', '321 fake st');
      formData.append('addressLine2', addressLine2);
      formData.append('city', city);
      formData.append('state', state);
      formData.append('zipCode', zipCode);


      const response = await fetch('http://localhost:8080/api/profile/update', {
        method: 'POST',
        // headers: {
        //   'Content-Type': 'application/json'
        // },
        // body: JSON.stringify(formData)
        body: formData
      });

      if (response.ok) {
        alert('Profile update successful');
      } else {
        const errorData = await response.json();
        alert('Profile update failed:', errorData.message);
      }
    } catch (error) {
      alert('Profile update failed:', error);
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
          <input className="Input" type="text" value={zipCode} onChange={(e) => setZipCode(e.target.value)} required />
        </div>

        <button type="submit" className="border-solid shadow w-full cursor-pointer text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default ProfileManagementPage;
