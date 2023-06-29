import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";
import * as NavigationMenu from '@radix-ui/react-navigation-menu';
import ProfileManagementPage from './routes/ProfileManagementPage.jsx';
import FuelQuoteHistoryPage from './routes/FuelQuoteHistoryPage.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Hello world!</div>,
  },
  {
    path: "/ProfileManagement",
    element: <ProfileManagementPage />,
  },
  {
    path: "/FuelQuoteHistory",
    element: <FuelQuoteHistoryPage />
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <div className='SiteContainer h-screen flex flex-col justify-start items-center bg-gradient-to-r from-rose-400 to-orange-300'>
    <NavigationMenu.Root style={{ 
      position: 'sticky', 
      top: '10px', 
      width: '90%', 
      height: '50px',
      margin: '0 auto', 
      zIndex: 1000, 
      backgroundColor: 'var(--black-a5)', 
      borderRadius: '4px',
      boxShadow: '0 0 0 1px var(--black-a9)'
    }}>
      <NavigationMenu.List style={{ 
        display: 'flex', 
        justifyContent: 'flex-start', 
        listStyleType: 'none', 
        padding: '15px 20px',
        gap: '20px'
      }}>
        <NavigationMenu.Link style={{
          color: 'white', 
          padding: '0 10px',
          lineHeight: '20px',
          fontSize: '15px',
          height: '20px'
        }} 
        onClick={() => router.navigate("/")}>Home</NavigationMenu.Link>
        <NavigationMenu.Link style={{
          color: 'white', 
          padding: '0 10px',
          lineHeight: '20px',
          fontSize: '15px',
          height: '20px'
        }} 
        onClick={() => router.navigate("/ProfileManagement")}>Profile Management</NavigationMenu.Link>
        <NavigationMenu.Link style={{
          color: 'white', 
          padding: '0 10px',
          lineHeight: '20px',
          fontSize: '15px',
          height: '20px'
        }} 
        onClick={() => router.navigate("/FuelQuoteHistory")}>Fuel Quote History</NavigationMenu.Link>
      </NavigationMenu.List>
    </NavigationMenu.Root>
    <div className='ContentContainer flex-grow flex justify-center items-center'>
      <React.StrictMode>
        <RouterProvider router={router} />
      </React.StrictMode>
    </div>
  </div>
)