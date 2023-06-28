import * as Form from '@radix-ui/react-form';
import React from 'react';
import '../styles/ProfileManagementPageStyles.css'

const FormDemo = () => (
  <Form.Root className="FormRoot">
    <Form.Field className="FormField" name="email">
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
    <Form.Field className="FormField" name="Address">
      <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
        <Form.Label className="FormLabel">Address Line 2</Form.Label>
      </div>
      <Form.Control asChild>
        <input className="Input" required />
        {/* <textarea className="Textarea" required /> */}
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
        <input className="Input" type="email" required />
        {/* <textarea className="Textarea" required /> */}
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
        <input className="Input" type="email" required />
        {/* <textarea className="Textarea" required /> */}
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
        <input className="Input" type="email" required />
        {/* <textarea className="Textarea" required /> */}
      </Form.Control>
    </Form.Field>
    {/* <Form.Submit asChild>
      <button className="Button" style={{ marginTop: 10 }}>
        Post question
      </button>
    </Form.Submit> */}
  </Form.Root>
);

export default FormDemo;