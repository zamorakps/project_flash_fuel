// import * as Form from '@radix-ui/react-form';
// import '../styles/FuelQuoteFormPageStyles.css'

// const FuelQuoteForm = () => {
//   return (
//     <div className="FormContainer w-1/3">
//       <h1 className="FormTitle">Fuel Quote Form</h1>
//       <Form.Root className="FormRoot">
//         <Form.Field className="FormField" name="GallonsRequested">
//           <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
//             <Form.Label className="FormLabel">Gallons Requested</Form.Label>
//             <Form.Message className="FormMessage" match="valueMissing">
//               Please enter the number of gallons requested.
//             </Form.Message>
//           </div>
//           <Form.Control asChild>
//             <input className="Input" type="number" required />
//           </Form.Control>
//         </Form.Field>
//         <Form.Field className="FormField" name="DeliveryAddress">
//           <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
//             <Form.Label className="FormLabel">Delivery Address</Form.Label>
//             <Form.Message className="FormMessage" match="valueMissing">
//               Please enter a delivery address.
//             </Form.Message>
//           </div>
//           <Form.Control asChild>
//             <input className="Input" value="" readOnly />
//           </Form.Control>
//         </Form.Field>
//         <Form.Field className="FormField" name="DeliveryDate">
//           <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
//             <Form.Label className="FormLabel">Delivery Date</Form.Label>
//             <Form.Message className="FormMessage" match="valueMissing">
//               Please select a delivery date.
//             </Form.Message>
//           </div>
//           <Form.Control asChild>
//             <input className="Input" type="date" required />
//           </Form.Control>
//         </Form.Field>
//         <Form.Field className="FormField" name="SuggestedPrice">
//           <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
//             <Form.Label className="FormLabel">Suggested Price / gallon</Form.Label>
//           </div>
//           <Form.Control asChild>
//             <input className="Input" type="number" readOnly />
//           </Form.Control>
//         </Form.Field>
//         <Form.Field className="FormField" name="TotalAmountDue">
//           <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
//             <Form.Label className="FormLabel">Total Amount Due</Form.Label>
//           </div>
//           <Form.Control asChild>
//             <input className="Input" type="number" readOnly />
//           </Form.Control>
//         </Form.Field>
//         <Form.Submit asChild>
//           <button className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
//             Submit
//           </button>
//         </Form.Submit>
//       </Form.Root>
//     </div>
//   );
// };

// export default FuelQuoteForm;




/*

import { useState, useEffect } from 'react';

const FuelQuoteFormPage = () => {
  const [gallons, setGallons] = useState('');
  const [deliveryDate, setDeliveryDate] = useState('');
  const [suggestedPrice, setSuggestedPrice] = useState('');
  const [totalAmountDue, setTotalAmountDue] = useState('');

  useEffect(() => {
    // This is a placeholder function, replace this with your actual function to calculate the suggested price and total price
    const calculatePrices = async () => {
      setSuggestedPrice('2.5'); // Set your calculated suggested price here
      setTotalAmountDue('250'); // Set your calculated total price here
    };

    calculatePrices();
  }, [gallons, deliveryDate]); // Recalculate prices whenever gallons or deliveryDate changes

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const formData = {
        username: 'admin',
        gallons,
        deliveryDate,
        suggestedPrice,
        totalAmountDue
      };

      const response = await fetch('http://localhost:8080/api/fuelquote/new', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (response.ok) {
        alert('Fuel quote submitted successfully');
      } else {
        const errorData = await response.json();
        alert('Fuel quote submission failed:', errorData.message);
      }
    } catch (error) {
      alert('Fuel quote submission failed:', error);
    }
  };

  return (
    <div className="FormContainer w-1/3 mb-6">
      <h1 className="FormTitle">Fuel Quote Form</h1>
      <form onSubmit={handleSubmit}>
        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Gallons</label>
          </div>
          <input className="Input" type="number" value={gallons} onChange={(e) => setGallons(e.target.value)} required />
        </div>

        <div className="FormField">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <label className="FormLabel">Delivery Date</label>
          </div>
          <input className="Input" type="date" value={deliveryDate} onChange={(e) => setDeliveryDate(e.target.value)} required />
        </div>

        <div className="FormField">
          <label className="FormLabel">Suggested Price</label>
          <input className="Input" type="number" value={suggestedPrice} readOnly />
        </div>

        <div className="FormField">
          <label className="FormLabel">Total Price</label>
          <input className="Input" type="number" value={totalAmountDue} readOnly />
        </div>

        <button type="submit" className="border-solid shadow w-full cursor-pointer text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default FuelQuoteFormPage;
*/


/*

import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import * as Form from '@radix-ui/react-form';
import '../styles/FuelQuoteFormPageStyles.css'

const FuelQuoteForm = () => {
  const [userProfile, setUserProfile] = useState({});
  const [suggestedPrice, setSuggestedPrice] = useState(0);
  const [totalAmountDue, setTotalAmountDue] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/profile/get?username=testUser")
      .then(response => response.json())
      .then(data => setUserProfile(data));
  }, []);

const handleSubmit = (event) => {
  event.preventDefault();
  const formData = new FormData(event.target);

  const fuelQuoteRequest = {
    "username": "testUser",
    "gallonsRequested": formData.get("GallonsRequested"),
    "deliveryAddress": userProfile.address, // fetched from profile
    "deliveryDate": formData.get("DeliveryDate"),
  };

    // Calculate the suggested price and total amount due based on the user's input
    // For now, we'll just set the suggested price to 2.0 and the total amount due to the suggested price times the number of gallons
    const gallonsRequested = Number(formData.get("GallonsRequested"));
    setSuggestedPrice(2.0);
    setTotalAmountDue(2.0 * gallonsRequested);

  fetch("/api/fuelquote/new", {
    method: "POST",
    body: JSON.stringify(fuelQuoteRequest),
    headers: {
      "Content-Type": "application/json"
    }
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    console.log('Fuel quote added:', data);
    navigate.push('/fuelquote/history');
  })
  .catch(error => {
    console.log('There was a problem with the request:', error);
  });
};

return (
  <div className="FormContainer w-1/3">
    <h1 className="FormTitle">Fuel Quote Form</h1>
    <form className="FormRoot" onSubmit={handleSubmit}>
      <Form.Field className="FormField" name="GallonsRequested">
        <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
          <Form.Label className="FormLabel">Gallons Requested</Form.Label>
          <Form.Message className="FormMessage" match="valueMissing">
            Please enter the number of gallons requested.
          </Form.Message>
        </div>
        <Form.Control asChild>
          <input className="Input" type="number" required />
        </Form.Control>
      </Form.Field>
      <Form.Field className="FormField" name="DeliveryAddress">
        <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
          <Form.Label className="FormLabel">Delivery Address</Form.Label>
          <Form.Message className="FormMessage" match="valueMissing">
            Please enter a delivery address.
          </Form.Message>
        </div>
        <Form.Control asChild>
          <input className="Input" value={userProfile.address || ''} readOnly />
        </Form.Control>
      </Form.Field>
      <Form.Field className="FormField" name="DeliveryDate">
        <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
          <Form.Label className="FormLabel">Delivery Date</Form.Label>
          <Form.Message className="FormMessage" match="valueMissing">
            Please select a delivery date.
          </Form.Message>
        </div>
        <Form.Control asChild>
          <input className="Input" type="date" required />
        </Form.Control>
      </Form.Field>
      <Form.Field className="FormField" name="SuggestedPrice">
        <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
          <Form.Label className="FormLabel">Suggested Price / gallon</Form.Label>
        </div>
        <Form.Control asChild>
          <input className="Input" type="number" readOnly value={suggestedPrice} />
        </Form.Control>
      </Form.Field>
      <Form.Field className="FormField" name="TotalAmountDue">
        <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
          <Form.Label className="FormLabel">Total Amount Due</Form.Label>
        </div>
        <Form.Control asChild>
          <input className="Input" type="number" readOnly value={totalAmountDue} />
        </Form.Control>
      </Form.Field>
      <Form.Submit asChild>
        <button className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
          Submit
        </button>
      </Form.Submit>
    </form>
  </div>
);
};

export default FuelQuoteForm;
*/

/*
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/FuelQuoteFormPageStyles.css'

const FuelQuoteForm = () => {
  const [userProfile, setUserProfile] = useState({});
  const [suggestedPrice, setSuggestedPrice] = useState(0);
  const [totalAmountDue, setTotalAmountDue] = useState(0);
  const [gallonsRequested, setGallonsRequested] = useState("");
  const [deliveryDate, setDeliveryDate] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/profile?username=testUser")
      .then(response => response.json())
      .then(data => {
        setUserProfile(data);
        console.log('User profile:', data);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    const fuelQuoteRequest = {
      "username": "testUser",
      "gallonsRequested": gallonsRequested,
      "deliveryAddress": userProfile.address,
      "deliveryDate": deliveryDate,
    };

    const gallons = Number(gallonsRequested);
    setSuggestedPrice(2.0);
    setTotalAmountDue(2.0 * gallons);

    fetch("/api/fuelquote/new", {
      method: "POST",
      body: JSON.stringify(fuelQuoteRequest),
      headers: {
        "Content-Type": "application/json"
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('Fuel quote added:', data);
      navigate('/fuelquote/history');
    })
    .catch(error => {
      console.log('There was a problem with the request:', error);
    });
  };

  return (
    <div className="FormContainer w-1/3">
      <h1 className="FormTitle">Fuel Quote Form</h1>
      <form className="FormRoot" onSubmit={handleSubmit}>
        <div className="FormField">
          <label className="FormLabel">Gallons Requested</label>
          <input className="Input" type="number" value={gallonsRequested} onChange={(e) => setGallonsRequested(e.target.value)} required />
        </div>
        <div className="FormField">
          <label className="FormLabel">Delivery Address</label>
          <input className="Input" value={userProfile.address || ''} readOnly />
        </div>
        <div className="FormField">
          <label className="FormLabel">Delivery Date</label>
          <input className="Input" type="date" value={deliveryDate} onChange={(e) => setDeliveryDate(e.target.value)} required />
        </div>
        <div className="FormField">
          <label className="FormLabel">Suggested Price / gallon</label>
          <input className="Input" type="number" readOnly value={suggestedPrice} />
        </div>
        <div className="FormField">
          <label className="FormLabel">Total Amount Due</label>
          <input className="Input" type="number" readOnly value={totalAmountDue} />
        </div>
        <button type="submit" className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default FuelQuoteForm;
*/

import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/FuelQuoteFormPageStyles.css'

const FuelQuoteForm = () => {
  const [userProfile, setUserProfile] = useState({});
  const [gallonsRequested, setGallonsRequested] = useState("");
  const [deliveryDate, setDeliveryDate] = useState("");
  const [suggestedPrice, setSuggestedPrice] = useState("");
  const [totalAmountDue, setTotalAmountDue] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetch("/api/user/profile?username=testUser") // updated API path to match your Spring Boot Controller
      .then(response => response.json())
      .then(data => {
        setUserProfile(data);
        console.log('User profile:', data);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    const fuelQuoteRequest = {
      "username": "testUser",
      "gallonsRequested": gallonsRequested,
      "deliveryAddress": userProfile.address,
      "deliveryDate": deliveryDate,
    };

    fetch("/api/fuelquote/new", {
      method: "POST",
      body: JSON.stringify(fuelQuoteRequest),
      headers: {
        "Content-Type": "application/json"
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log('Fuel quote added:', data);
      // Set new state variables
      setSuggestedPrice(data.suggestedPrice);
      setTotalAmountDue(data.totalAmountDue);
      navigate('/fuelquote/history');
    })
    .catch(error => {
      console.log('There was a problem with the request:', error);
    });
  };

  return (
    <div className="FormContainer w-1/3">
      <h1 className="FormTitle">Fuel Quote Form</h1>
      <form className="FormRoot" onSubmit={handleSubmit}>
        <div className="FormField">
          <label className="FormLabel">Gallons Requested</label>
          <input className="Input" type="number" value={gallonsRequested} onChange={(e) => setGallonsRequested(e.target.value)} required />
        </div>
        <div className="FormField">
          <label className="FormLabel">Delivery Address</label>
          <input className="Input" value={userProfile.address || ''} readOnly />
        </div>
        <div className="FormField">
          <label className="FormLabel">Delivery Date</label>
          <input className="Input" type="date" value={deliveryDate} onChange={(e) => setDeliveryDate(e.target.value)} required />
        </div>
        <div className="FormField">
          <label className="FormLabel">Suggested Price per Gallon</label>
          <input className="Input" value={suggestedPrice || ''} readOnly />
        </div>
        <div className="FormField">
          <label className="FormLabel">Total Amount Due</label>
          <input className="Input" value={totalAmountDue || ''} readOnly />
        </div>
        <button type="submit" className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
          Submit
        </button>
      </form>
    </div>
  );
};

export default FuelQuoteForm;
