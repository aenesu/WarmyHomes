import Link from "next/link"
import styles from "./need-help.module.scss"

export default function NeedHelp() {
    return (
        <div className={styles.needHelpContainer} >

            <div className={styles.textGroup}>
                <div className={styles.title}>Need help? Talk to our expert.</div>
                <div className={styles.text}>Talk to our experts or Browse through more properties.</div>
            </div>

            <div className={styles.buttonGroup}>
                <Link href="/contact">
                    <button className={`${styles.contactUsLink} ${styles.button}`}>
                        <span>Contact Us</span>
                        <img src="/assets/vectors/right-upwards-arrow.svg" alt="Contact Us Page" width={21} height={21}></img>
                    </button>
                </Link>
                <a href="tel:+1 555 55 55">
                    <button className={`${styles.contactUsCall} ${styles.button}`}>
                        <img src="/assets/vectors/phone.svg" alt="Call Us" width={21} height={21}></img>
                        <span>Contact Us</span>
                    </button>
                </a>
            </div>
        </div>
    )
}
