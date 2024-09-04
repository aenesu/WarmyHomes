import styles from "./tour-requests-detail.module.scss";
import Banner from "@/components/common/banner/banner";
import Image from "next/image";

export default function TourDetails() {
  return (
    <div className={styles.container}>
      <Banner title={"MY TOUR REQUESTS"} />
      <div className={styles.content}>
        <div className={styles.imageContainer}>
          <Image 
            src="/assets/images/house-isolated-field 6.png" 
            width={500} 
            height={300} 
            alt="House in isolated field" 
          />
          <div className={styles.statusBox}>Pending</div>
        </div>
        <div className={styles.detailsContainer}>
          <div className={styles.header}>
            <h5 className={styles.title}>Equestrian Family Home</h5>
            <span className={styles.price}>$1400.00</span>
          </div>
          <p className={styles.location}>California City, CA, USA</p>
          
          <div className={styles.form}>
            <div className={styles.formGroup}>
              <label htmlFor="tourDate">Tour Date</label>
              <input type="date" id="tourDate" className={styles.inputBox} />
            </div>
            <div className={styles.formGroup}>
              <label htmlFor="tourTime">Tour Time</label>
              <input type="time" id="tourTime" className={styles.inputBox} />
            </div>
          </div>

          <div className={styles.buttonContainer}>
            <button className={styles.cancelButton}>Cancel</button>
            <button className={styles.updateButton}>Update</button>
          </div>
        </div>
      </div>
    </div>
  );
}
