import styles from "./admin-tour-detail.module.scss";
import Image from "next/image";

export default function AdminTourDetails() {
  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <div className={styles.imageContainer}>
          <Image 
            src="/assets/images/house-placeholder.jpg" 
            width={500} 
            height={350} 
            alt="House in isolated field" 
          />
          <div className={styles.statusBox}>Pending</div>
        </div>
        <div className={styles.detailsContainer}>
          <div className={styles.titlePriceContainer}>
            <div className={styles.title}>Equestrian Family Home</div>
            <div className={styles.price}>$1400.00</div>
          </div>
          <div className={styles.location}>California City, CA, USA</div>
          <div className={styles.form}>
            <div className={styles.formGroup}>
              <label className={styles.label}>Tour Date</label>
              <input type="date" className={styles.inputBox} />
            </div>
            <div className={styles.formGroup}>
              <label className={styles.label}>Tour Time</label>
              <input type="time" className={styles.inputBox} />
            </div>
          </div>
          <div className={styles.buttonContainer}>
            <button className={styles.updateButton}>Delete</button>
          </div>
        </div>
      </div>
    </div>
  );
}
