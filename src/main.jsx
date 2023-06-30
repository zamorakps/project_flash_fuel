import React from 'react'
import ReactDOM from 'react-dom/client'
// import App from './App.jsx'

import './index.css'
import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";
import * as NavigationMenu from '@radix-ui/react-navigation-menu';
import ProfileManagementPage from './routes/ProfileManagementPage.jsx';
import FuelQuoteHistoryPage from './routes/FuelQuoteHistoryPage.jsx';
import FuelQuoteFormPage from './routes/FuelQuoteFormPage.jsx';
const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Home Page</div>,
    title: "Home"
  },
  {
    path: "/ProfileManagement",
    element: <ProfileManagementPage />,
    title: "Profile Management"
  },
  {
    path: "/FuelQuoteHistory",
    element: <FuelQuoteHistoryPage />,
    title: "Fuel Quote History",
  },
  {
    path: "/FuelQuoteForm",
    element: <FuelQuoteFormPage />,
    title: "Fuel Quote Form"
  },
]);

const headerLinks = router.routes.map((route) => (
  <NavigationMenu.Link
    key={route.path}
    style={{
      color: 'white',
      padding: '0 10px',
      lineHeight: '20px',
      fontSize: '15px',
      height: '20px',
      cursor: 'pointer'
    }}
    onClick={() => router.navigate(route.path)}
  >
    {route.title}
  </NavigationMenu.Link>
));

ReactDOM.createRoot(document.getElementById('root')).render(
  <div className='SiteContainer h-screen flex flex-col justify-start items-center bg-gradient-to-r from-rose-400 to-orange-300'>
    <NavigationMenu.Root
      style={{
        position: 'sticky',
        top: '10px',
        width: '90%',
        height: '50px',
        margin: '0 auto',
        zIndex: 1000,
        backgroundColor: 'var(--black-a5)',
        borderRadius: '4px',
        boxShadow: '0 0 0 1px var(--black-a9)',
      }}
    >
      <NavigationMenu.List
        style={{
          display: 'flex',
          justifyContent: 'flex-start',
          listStyleType: 'none',
          padding: '15px 20px',
          gap: '20px',
        }}
      >
        {headerLinks}
      </NavigationMenu.List>
    </NavigationMenu.Root>
    <div className='ContentContainer flex-grow flex justify-center items-center' style={{ width: '100%' }}>
      <React.StrictMode>
        <RouterProvider router={router} />
      </React.StrictMode>
    </div>
  </div>
);
