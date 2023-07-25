import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";
import * as NavigationMenu from '@radix-ui/react-navigation-menu';
import HomePage from './routes/HomePage.jsx';
import ProfileManagementPage from './routes/ProfileManagementPage.jsx';
import FuelQuoteHistoryPage from './routes/FuelQuoteHistoryPage.jsx';
import FuelQuoteFormPage from './routes/FuelQuoteFormPage.jsx';
import RegistrationFormPage from './routes/RegistrationFormPage';
import LoginFormPage from './routes/LoginFormPage';
import LogoutPage from './routes/LogoutPage';

const token = !!document.cookie.split('; ').find(row => row.startsWith('token='))?.split('=')[1];
const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage />,
    title: "Home"
  },
  {
    path: "/profile",
    element: <ProfileManagementPage />,
    title: "Profile",
    class: "profile",
    group: 1,
    requireLogin: true
  },
  {
    path: "/fuelquotehistory",
    element: <FuelQuoteHistoryPage />,
    title: "Fuel Quote History",
    requireLogin: true
  },
  {
    path: "/fuelquoteform",
    element: <FuelQuoteFormPage />,
    title: "Fuel Quote Form",
    requireLogin: true
  },
  {
    path: "/registration",
    element: <RegistrationFormPage />,
    title: "Register",
    group: 1,
    class: "profile",
    requireLogin: false
  },
  {
    path: "/login",
    element: <LoginFormPage />,
    title: "Login",
    group: 1,
    class: "profile",
    requireLogin: false
  },
  {
    path: "/logout",
    element: <LogoutPage />,
    title: "Logout",
    group: 1,
    class: "profile",
    requireLogin: true
  },
]);

function getCurrentPath() {
  const location = window.location;
  const pathSegments = location.href.split('/');
  return '/' + pathSegments[pathSegments.length - 1];
}

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

    if (getCurrentPath().toLowerCase() === route.path.toLowerCase()) {
      if (!token && route.requireLogin === true) {
        location.href = './login';
      }
    }

    if (route.requireLogin == token || route.requireLogin == undefined) {
      return (
        <NavigationMenu.Link
          key={`${group[0].class}-${group[0].group}-${route.path}`}
          onClick={() => router.navigate(route.path)}
          className={linkClass}
        >
          {route.title}
        </NavigationMenu.Link>
      );
    }
  });

  return <div className={group[0].class} key={`${group[0].class}-${group[0].group}`}>{groupLinks}</div>;
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