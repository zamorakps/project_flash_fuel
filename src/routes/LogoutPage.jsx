import { useEffect, useState } from 'react';
import '../styles/LogoutPage.css';
import '../styles/Main.css';

const LogoutPage = () => {
    const [countdown, setCountdown] = useState(3);

    const clearTokenFromCookie = () => {
        document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    };

    useEffect(() => {
        clearTokenFromCookie();

        const timer = setInterval(() => {
            setCountdown((prevCountdown) => prevCountdown - 1);
        }, 1000);

        return () => {
            clearInterval(timer);
        };
    }, []);

    useEffect(() => {
        if (countdown === 0) {
            window.location.href = './';
        }
    }, [countdown]);

    return (
        <div>
            <h2 className="logoutTitle">Logged out successfully.</h2>
            <p className="logoutTimer">You will be redirected in {countdown} seconds...</p>
        </div>
    );
};

export default LogoutPage;
