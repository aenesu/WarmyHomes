import Link from "next/link"
import styles from "./property-city-card.module.scss"

export default function PropertyCityCard({ index, city, amount, link }) {
  return (
    <Link href={link} className={styles.noUnderline}>
      <div className={styles.card}>
        <div className={styles.circle}></div>
        <div className={styles.text}>

          <div className={styles.city}>{city}</div>
          <div className={styles.amount}>{amount} properties</div>
        </div>
      </div>
    </Link>
  )
}
