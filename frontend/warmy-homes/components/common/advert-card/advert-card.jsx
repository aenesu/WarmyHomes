import styles from "./advert-card.module.scss";

export default function AdvertCard({ id, title, city, district, price }) {
  return (
    <>
      <div className={styles.cardContainer}>
        <div className={styles.cardTextBox}>
          <div className={styles.cardText}>
            <div className={styles.cardTitle}>{title}</div>
            <div className={styles.cardSubTitle}>{city}, {district}</div>
          </div>
          <div className={styles.cardPrice}>$ {price}</div>
        </div>
      </div>
    </>
  )
}
