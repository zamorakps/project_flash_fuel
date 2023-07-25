import '../styles/HomePage.css';
import '../styles/Main.css';

const HomePage = () => {
    const token = !!document.cookie.split('; ').find(row => row.startsWith('token='))?.split('=')[1];

    return (
        <div className="home-page">
            <h1 className="title">Welcome to Flash Fuel</h1>
            <p className="description">
                We provide fast and reliable fuel delivery services at your doorstep.
            </p>
            {token ? (
                <a href="./profile" className="btnProfile">
                    PROFILE
                </a>
            ) : (
                <a href="./login" className="btnLogin">
                    LOGIN
                </a>
            )}
        </div>
    );
};

export default HomePage;
