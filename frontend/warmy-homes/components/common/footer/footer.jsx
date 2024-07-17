import styles from "./footer.module.scss";
import { FaAppStore, FaGooglePlay } from "react-icons/fa";

export default function Footer() {
  return (
    <div className={styles.divClass}>
      <div className={`${styles.firstChild} ${styles.footerSegment}`}>
        <a href="/#">
        <img className={styles.logo} src="/assets/images/logo-white2.png" alt="Logo"></img>
        </a>
        <p className={styles.footerText}>
          Our slogan, 'Unlocking Your Home's Potential, Together,' reflects our
          dedication to helping clients realize the full potential of their real
          estate investments through collaborative and personalized services.{" "}
        </p>
        <div className={styles.flex}>
          <div className={styles.button}>
            App Store
            <div className={styles.storeLogo}><FaAppStore/></div>
          </div>
          <div className={styles.button}>
            Google Play
           <div className={styles.storeLogo}> <FaGooglePlay /> </div>
          </div>
        </div>
      </div>
      <div className={styles.footerSegment}>
        <h2>Quick Links</h2>
        <ul className={styles.linkList}>
        <li><a href="/#">
          Home
        </a></li>
        <li><a href="/properties">
          Properties
        </a></li>
        <li><a href="/about">
          About
        </a></li>
        <li><a href="/contact">
          Contact
        </a></li>
        <li><a href="/#">
          Privacy Policy
        </a></li>
        </ul>
      </div>
      <div className={styles.footerSegment}>
        <h4 className={styles.footerContactInfo}>Contact</h4>
        <p className={styles.footerContactInfo}>329 Queensberry Street, North Melbourne VIC 3051, Australia.</p>
        <a className={styles.footerContactInfo}>+1-123-456-456-65</a>
        <p className={styles.footerContactInfo}>
          {" "}
          <a>info@realestate.com</a>
        </p>
      </div>
    </div>
  );
}
