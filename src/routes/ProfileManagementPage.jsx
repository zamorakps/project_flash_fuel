import * as Form from '@radix-ui/react-form';
import React from 'react';
import '../styles/ProfileManagementPageStyles.css'


const FormDemo = () => {
  const zipCodeValidation = new RegExp("^(F)?\\d{5,}$");

  return (<Form.Root className="FormRoot w-1/5">
  <Form.Field className="FormField" name="Name">
    <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
      <Form.Label className="FormLabel">Name</Form.Label>
      <Form.Message className="FormMessage" match="valueMissing">
        Please enter your name.
      </Form.Message>
    </div>
    <Form.Control asChild>
      <input className="Input" required />
    </Form.Control>
  </Form.Field>
  <Form.Field className="FormField" name="Address">
    <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
      <Form.Label className="FormLabel">Address</Form.Label>
      <Form.Message className="FormMessage" match="valueMissing">
        Please enter an address
      </Form.Message>
    </div>
    <Form.Control asChild>
      <input className="Input" required />
      {/* <textarea className="Textarea" required /> */}
    </Form.Control>
  </Form.Field>
  <Form.Field className="FormField" name="AddressLine2">
    <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
      <Form.Label className="FormLabel">Address Line 2</Form.Label>
    </div>
    <Form.Control asChild>
      <input className="Input" />
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
      <input className="Input" required />
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
      <input className="Input" required />
    </Form.Control>
  </Form.Field>
  <Form.Field className="FormField" name="ZipCode">
    <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
      <Form.Label className="FormLabel">Zip Code</Form.Label>
      <Form.Message className="FormMessage" match={(value, formData) => value.length < 5 || !zipCodeValidation.test(value)}>Zip code must be at least 5 digits long.</Form.Message>
      {/* <Form.Message className="FormMessage" match={(value, formData) => value.length < 5}>Zip code must be at least 5 digits long.</Form.Message> */}
      <Form.Message className="FormMessage" match="valueMissing">Please enter a zip code.</Form.Message>
    </div>
    <Form.Control asChild>
      <input className="Input" required />
    </Form.Control>
  </Form.Field>
  <Form.Submit asChild>
    <button className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-sky-200 hover:text-cyan-600 text-cyan-600 font-semibold py-2 px-4 border border-cyan-600 hover:border-transparent rounded" style={{ marginTop: 10 }}>
      Submit
    </button>
  </Form.Submit>
</Form.Root>
)
};

export default FormDemo;