import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";
import * as NavigationMenu from '@radix-ui/react-navigation-menu';
import ProfileManagementPage from './routes/ProfileManagementPage.jsx';
import FuelQuoteHistoryPage from './routes/FuelQuoteHistoryPage.jsx';
import FuelQuoteFormPage from './routes/FuelQuoteFormPage.jsx';
import RegistrationFormPage from './routes/RegistrationFormPage';
import LoginFormPage from './routes/LoginFormPage';
const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Home Page</div>,
    title: "Home"
  },
  {
    path: "/ProfileManagement",
    element: <ProfileManagementPage />,
    title: "Profile"
  },
  {
    path: "/FuelQuoteHistory",
    element: <FuelQuoteHistoryPage />,
    title: "Fuel Quote History",
  },
  {
    path: "/FuelQuoteForm",
    element: <FuelQuoteFormPage />,
    title: "Fuel Quote Form",  
  },
  {
    path: "/RegistrationForm",
    element: <RegistrationFormPage />,
    title: "Register",
    group: 1,
    class: "profile"
  },
  {
    path: "/LoginForm",
    element: <LoginFormPage />,
    title: "Login",
    group: 1,
    class: "profile"
  },
]);

const groupedRoutes = {}; 

router.routes.forEach((route) => {
  if (!groupedRoutes[route.group]) {
    groupedRoutes[route.group] = [];
  }
  groupedRoutes[route.group].push(route);
});

const headerLinks = Object.values(groupedRoutes).map((group) => {
  const groupLinks = group.map((route) => {
    let linkClass = 'nav-item'; 
    if (route.group === 1) {
      linkClass += ` ${route.class}-item`; 
    }

    return (
      <NavigationMenu.Link
        key={route.path}
        onClick={() => router.navigate(route.path)}
        className={linkClass}
      >
        {route.title}
      </NavigationMenu.Link>
    );
  });

  return <div className={group[0].class} key={group[0].group}>{groupLinks}</div>;
});

ReactDOM.createRoot(document.getElementById('root')).render(
  <div className='SiteContainer h-screen flex flex-col justify-start items-center bg-gradient-to-r from-rose-400 to-orange-300'>
    <NavigationMenu.Root className="nav-root">
      <NavigationMenu.List className="nav-menu">
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
