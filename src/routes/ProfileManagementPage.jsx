import * as Form from '@radix-ui/react-form';
import { useState } from 'react';

const ProfileManagementPage = () => {
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [addressLine2, setAddressLine2] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [zipCode, setZipCode] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      // Create an object with the form data
      const formData = {
        name,
        address,
        addressLine2,
        city,
        state,
        zipCode
      };

      // Send a request to the backend with the form data
      const response = await fetch('/api/updateProfile', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (response.ok) {
        // Successful profile update
        console.log('Profile update successful');

        // Reset form fields
        setName('');
        setAddress('');
        setAddressLine2('');
        setCity('');
        setState('');
        setZipCode('');
      } else {
        // Failed profile update
        const errorData = await response.json();
        console.error('Profile update failed:', errorData.message);
      }
    } catch (error) {
      console.error('Profile update failed:', error);
    }
  };

  return (
    <div className="FormContainer w-1/3 mb-6">
      <h1 className="FormTitle">Profile Management</h1>
      <Form.Root className="FormRoot" onSubmit={handleSubmit}>
        <Form.Field className="FormField" name="Name">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Name</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              Please enter your name.
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" value={name} onChange={(e) => setName(e.target.value)} required />
          </Form.Control>
        </Form.Field>
        <Form.Field className="FormField" name="Address">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Address</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              Please enter an address.
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" value={address} onChange={(e) => setAddress(e.target.value)} required />
          </Form.Control>
        </Form.Field>
        <Form.Field className="FormField" name="AddressLine2">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Address Line 2</Form.Label>
          </div>
          <Form.Control asChild>
            <input className="Input" value={addressLine2} onChange={(e) => setAddressLine2(e.target.value)} />
          </Form.Control>
        </Form.Field>
        <Form.Field className="FormField" name="City">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">City</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              Please enter your city.
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" value={city} onChange={(e) => setCity(e.target.value)} required />
          </Form.Control>
        </Form.Field>
        <Form.Field className="FormField" name="State">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">State</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              Please enter your state.
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" value={state} onChange={(e) => setState(e.target.value)} required />
          </Form.Control>
        </Form.Field>
        <Form.Field className="FormField" name="ZipCode">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Zip Code</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              Please enter a zip code.
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" value={zipCode} onChange={(e) => setZipCode(e.target.value)} required />
          </Form.Control>
        </Form.Field>
        <Form.Submit asChild>
          <button className="border-solid shadow w-full cursor-pointer text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
            Submit
          </button>
        </Form.Submit>
      </Form.Root>
    </div>
  );
};

export default ProfileManagementPage;
