import * as Form from '@radix-ui/react-form';
// import React from 'react';
import '../styles/ProfileManagementPageStyles.css'


const FormDemo = () => {
  const zipCodeValidation = new RegExp("^(F)?\\d{5,}$");

  return (
    <div className="FormContainer w-1/3 mb-6">
      <h1 className="FormTitle">Profile Management</h1>
      <Form.Root className="FormRoot">
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
            {/* Removed the `formData` to prevent error */}
            <Form.Message className="FormMessage" match={(value) => value.length < 5 || !zipCodeValidation.test(value)}>Zip code must be at least 5 digits long.</Form.Message>
            {/* <Form.Message className="FormMessage" match={(value, formData) => value.length < 5}>Zip code must be at least 5 digits long.</Form.Message> */}
            <Form.Message className="FormMessage" match="valueMissing">Please enter a zip code.</Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" required />
          </Form.Control>
        </Form.Field>
        <Form.Submit asChild>
          <button className="border-solid shadow w-full cursor-pointer text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
            Submit
          </button>
        </Form.Submit>
      </Form.Root>
    </div>
  )
};

export default FormDemo;