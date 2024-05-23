import styles from './header.module.scss';
import AddPropertyButton from './addPropertyButton/addPropertyButton';
import { AiOutlineUser } from 'react-icons/ai';
import { Akshar } from 'next/font/google'; 

const akshar = Akshar({ subsets: ["latin"], weight: ["300", "400", "500", "600", "700"] });

const navLinks = [
  { name: 'Home', href: '/#' },
  { name: 'Properties', href: '/advertsPage' },
  { name: 'About', href: '/#' },
  { name: 'Contact', href: '/#' },
];

const Header = () => {
  return (
    <div className={`${styles.headerContainer} ${akshar.className}`}>
      <div className={styles.logo}>
        <img src="/assets/images/logo-white2.png" alt="Logo" className={styles.logoImage} />
      </div>
      <div className={styles.navContainer}>
        <ul className={styles.navbarNav}>
          {navLinks.map((link, index) => (
            <li key={index}>
              <a href={link.href} className={`${styles.navLink} ${akshar.className}`}>
                {link.name}
              </a>
            </li>
          ))}
        </ul>
      </div>
      <div className={styles.buttonsContainer}>
        <button className={`${styles.loginButton} ${akshar.className}`}>
          <AiOutlineUser className={styles.icon} /> Login&nbsp;/&nbsp;Register
        </button>
        <AddPropertyButton />
      </div>
    </div>
  );
};

export default Header;