import Link from "next/link";
import styles from "./footer.module.scss";
import { FaAppStore, FaGooglePlay } from "react-icons/fa";

export default function Footer() {
  return (
    <div className={styles.divClass}>
      <div className={`${styles.firstChild} ${styles.footerSegment}`}>
        <Link href="/">
          <img className={styles.logo} src="/assets/images/logo-white2.png" alt="Logo"></img>
        </Link>
        <p className={styles.footerText}>
          Our slogan, 'Unlocking Your Home's Potential, Together,' reflects our
          dedication to helping clients realize the full potential of their real
          estate investments through collaborative and personalized services.{" "}
        </p>
        <div className={styles.flex}>
          <a href={"https://www.apple.com/app-store/"} target="_blank" > <div className={styles.button}>
            App Store
            <div className={styles.storeLogo}><FaAppStore /></div>
          </div> </a>
          <a href={"https://play.google.com/"} target="_blank" > <div className={styles.button}>
            Google Play
            <div className={styles.storeLogo}> <FaGooglePlay /> </div>
          </div> </a>
        </div>
      </div>
      <div className={styles.footerSegment}>
        <h2>Quick Links</h2>
        <ul className={styles.linkList}>
          <li><Link href="/">
            Home
          </Link></li>
          <li><Link href="/properties">
            Properties
          </Link></li>
          <li><Link href="/about">
            About
          </Link></li>
          <li><Link href="/contact">
            Contact
          </Link></li>
          <li><Link href="/login">
            Privacy Policy
          </Link></li>
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
