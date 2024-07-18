import styles from './header.module.scss';
import AddPropertyButton from './addPropertyButton/addPropertyButton';
import { AiOutlineUser } from 'react-icons/ai';
import { Akshar } from 'next/font/google';
import Link from 'next/link';

const akshar = Akshar({ subsets: ["latin"], weight: ["300", "400", "500", "600", "700"] });

const navLinks = [
  { name: 'Home', href: '/#' },
  { name: 'Properties', href: '/properties' },
  { name: 'About', href: '/about' },
  { name: 'Contact', href: '/contact' },
];

const Header = () => {
  return (
    <div className={styles.mainContainer}>
      <div className={`${styles.headerContainer} ${akshar.className}`}>
        <div className={styles.logo}>
          <a href="/#">
            <img src="/assets/images/logo.png" alt="Logo" className={styles.logoImage} />
          </a>
        </div>
        <div className={styles.navContainer}>
          <ul className={styles.navbarNav}>
            {navLinks.map((link, index) => (
              <li className={styles.listitem} key={index}>
                <a href={link.href} className={`${styles.navLink} ${akshar.className}`}>
                  {link.name}
                </a>
              </li>
            ))}
          </ul>
        </div>
        <div className={`${styles.buttonsContainer} `}>
          <Link className={`${styles.loginButton}`} href="/login">
            <AiOutlineUser className={styles.icon} /> Login&nbsp;/&nbsp;Register
          </Link>
          <Link href="/new-advert" className={styles.addNewButton} ><AddPropertyButton /></Link>
        </div>
      </div>

    </div>
  );
};

export default Header;