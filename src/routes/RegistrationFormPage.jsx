import * as Form from '@radix-ui/react-form';

const RegistrationFormPage = () => {
  return (
    <div className="FormContainer w-1/3">
      <h1 className="FormTitle">Registration Form</h1>
      <Form.Root className="FormRoot">
        <Form.Field className="FormField" name="username">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Username</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              This field is required and should not exceed 50 characters
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" type="text" required maxLength={50} />
          </Form.Control>
        </Form.Field>

        <Form.Field className="FormField" name="password">
          <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between' }}>
            <Form.Label className="FormLabel">Password</Form.Label>
            <Form.Message className="FormMessage" match="valueMissing">
              This field is required and should not be less than 6 characters
            </Form.Message>
          </div>
          <Form.Control asChild>
            <input className="Input" type="password" required minLength={6} />
          </Form.Control>
        </Form.Field>

        <Form.Submit asChild>
          <button className="border-solid shadow w-full text-center duration-300 -transparent hover:bg-white hover:text-black text-white font-semibold py-2 px-4 border border-lightgray hover:border-transparent rounded" style={{ marginTop: 10 }}>
            Register
          </button>
        </Form.Submit>
      </Form.Root>
    </div>
  );
};

export default RegistrationFormPage;
