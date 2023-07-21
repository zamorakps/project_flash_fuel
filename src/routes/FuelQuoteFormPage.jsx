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

  const loggedUserId = 6;

  useEffect(() => {
    fetch(`http://localhost:8080/api/user/profile?userId=${loggedUserId}`)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        const clientInfo = data;

        let fullAddress = `${clientInfo.address}, `;
        if (clientInfo.addressLine2) {
          fullAddress += `${clientInfo.addressLine2}, `;
        }
        fullAddress += `${clientInfo.city}, ${clientInfo.state}, ${clientInfo.zipCode}`;        
        
        const fullUserProfile = {
          ...data,
          fullAddress,
        };

        setUserProfile(fullUserProfile);
        console.log('User profile:', fullUserProfile);
      });
  }, []);

  useEffect(() => {
    if(gallonsRequested !== "") {
      fetch("http://localhost:8080/api/fuelquote/calculate", {
        method: "POST",
        body: JSON.stringify({"gallonsRequested": gallonsRequested}),
        headers: {
          "Content-Type": "application/json"
        }
      })
      .then(response => response.json())
      .then(data => {
        console.log('Calculated prices:', data);
        setSuggestedPrice(data.suggestedPrice);
        setTotalAmountDue(data.totalAmountDue);
      })
      .catch(error => {
        console.log('There was a problem with the calculation request:', error);
      });
    }
  }, [gallonsRequested]);

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('handleSubmit triggered');

    const fuelQuoteRequest = {
      "userId": loggedUserId,
      "gallonsRequested": Number(gallonsRequested),
      "deliveryAddress": userProfile.fullAddress,
      "deliveryDate": deliveryDate,
      "suggestedPrice": suggestedPrice,
      "totalAmountDue": totalAmountDue,
    };

    console.log(fuelQuoteRequest);

    fetch("http://localhost:8080/api/fuelquote/new", {
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
      navigate('/FuelQuoteHistory');
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
          <input className="Input" value={userProfile.fullAddress || ''} readOnly />
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



/*
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

  const loggedUserId = 'testUser';

  useEffect(() => {
    fetch(`http://localhost:8080/api/user/profile?username=${loggedUserId}`)
      .then(response => response.json())
      .then(data => {
        setUserProfile(data);
        console.log('User profile:', data);
      });
  }, []);

  useEffect(() => {
    if(gallonsRequested !== "") {
      fetch("http://localhost:8080/api/fuelquote/calculate", {
        method: "POST",
        body: JSON.stringify({"gallonsRequested": gallonsRequested}),
        headers: {
          "Content-Type": "application/json"
        }
      })
      .then(response => response.json())
      .then(data => {
        console.log('Calculated prices:', data);
        setSuggestedPrice(data.suggestedPrice);
        setTotalAmountDue(data.totalAmountDue);
      })
      .catch(error => {
        console.log('There was a problem with the calculation request:', error);
      });
    }
  }, [gallonsRequested]);

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('handleSubmit triggered');

    const fuelQuoteRequest = {
      "username": loggedUserId,
      "gallonsRequested": Number(gallonsRequested),
      "deliveryAddress": userProfile.address,
      "deliveryDate": deliveryDate,
      "suggestedPrice": suggestedPrice,
      "totalAmountDue": totalAmountDue,
    };

    console.log(fuelQuoteRequest);

    fetch("http://localhost:8080/api/fuelquote/new", {
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
      // navigate('/fuelquote/history');
      navigate('/FuelQuoteHistory');
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
*/