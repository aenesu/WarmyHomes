import styles from "./footer.module.scss"
import { FaAppStore, FaGooglePlay } from "react-icons/fa";


export default function Footer() {
  return (
    <div className={styles.divClass} >
      
      <div className={styles.footerSegment}>
      <img src="/assets/images/logo-white2.png"></img>
      <p>Our slogan, 'Unlocking Your Home's Potential, Together,' reflects our dedication to helping clients realize the full potential of their real estate investments through collaborative and personalized services. </p>
      <div>
        <div className="button">
        <FaAppStore />
        </div>
        <div className="button">
        <FaGooglePlay />
        </div>
      </div>
      </div>
      <div className={styles.footerSegment}> 
      
      </div>
      <div className={styles.footerSegment}>
      <FaGooglePlay />
      </div>

      

    </div>
  )
}
