import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
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
  <div className='SiteContainer h-screen flex justify-center items-center bg-gradient-to-r from-rose-400 to-orange-300'>
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
  </div>
)
