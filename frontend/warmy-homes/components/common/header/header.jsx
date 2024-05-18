import styles from './header.module.scss';
import AddPropertyButton from './addPropertyButton/addPropertyButton';
import { AiOutlineUser } from 'react-icons/ai';

const Header = () => {
  return (
    <div className={styles.headerContainer}>
      <div className={styles.logo}>
        <img src="/assets/images/logo-white2.png" alt="Logo" className={styles.logoImage} />
      </div>
      <div className={styles.navContainer}>
        <ul className={styles.navbarNav}>
          <li>
            <a className="nav-link" href='/#'>Home</a>
          </li>
          <li>
            <a className="nav-link" href="/#">Properties</a>
          </li>
          <li>
            <a className="nav-link" href="/#">About</a>
          </li>
          <li>
            <a className="nav-link" href="/#">Contact</a>
          </li>
        </ul>
      </div>
      <div className={styles.buttonsContainer}>
        <button className={styles.loginButton}>
          <AiOutlineUser className={styles.icon} /> Login/Register
        </button>
        <AddPropertyButton />
      </div>
    </div>
  );
};

export default Header;
