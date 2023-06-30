import * as Form from '@radix-ui/react-form';
import '../styles/FuelQuoteFormPageStyles.css'

const FuelQuoteForm = () => {
  return (
    <div className="FormContainer w-1/3">
      <h1 className="FormTitle">Fuel Quote Form</h1>
      <Form.Root className="FormRoot">
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
            <input className="Input" value="" readOnly />
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
            <input className="Input" type="number" readOnly />
          </Form.Control>
        </Form.Field>
        <Form.Field className="FormField" name="TotalAmountDue">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Total Amount Due</Form.Label>
          </div>
          <Form.Control asChild>
            <input className="Input" type="number" readOnly />
          </Form.Control>
        </Form.Field>
        <Form.Submit asChild>
          <button className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
            Submit
          </button>
        </Form.Submit>
      </Form.Root>
    </div>
  );
};

export default FuelQuoteForm;