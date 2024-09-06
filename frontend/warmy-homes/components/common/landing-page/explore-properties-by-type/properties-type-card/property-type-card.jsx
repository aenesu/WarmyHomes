import Image from "next/image";
import styles from "./property-type-card.module.scss";
import Link from "next/link";


export default function PropertyTypeCard({ index, title, icon, count, link}) {
  return (
    <Link className={styles.noUnderline} href={link}>
    <div className={styles.typeCard}>
      <div className={styles.typeIcon}>
        <Image src={icon} alt={title} width={36} height={36} />
      </div>
      <div className={styles.typeCategory}>
        <span className={styles.categoryName}>{title}</span>
        <span className={styles.propertyCount}>{count} Properties</span>
      </div>
    </div>
    </Link>
  )
}
