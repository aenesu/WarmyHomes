import Link from "next/link";
import styles from "./adverts-card.module.scss";

export default function AdvertsCard({
  index,
  title,
  city_id,
  location,
  price,
  create_at,
  status,
  view_count,
}) {
  return (
    <div className={styles.card}>
      <div className={styles.container}>
        <Link href="/properties/slug" target="_blank" className={styles.link}>
          <img
            src="/assets/images/house-isolated-field 6.png"
            alt={title}
            className={styles.image}
          />
        </Link>
        <div>
          <Link href="/properties/slug" target="_blank" className={styles.link}> <div className={styles.title}>{title}</div></Link>
          <div className={styles.city_id}>{city_id}</div>
          <div className={styles.location}>{location}</div>
          <div className={styles.price}>{price}</div>
        </div>
      </div>
      <div className={styles.datePublished}>{create_at}</div>
      <div className={styles.status}>{status}</div>
      <div className={styles.iconsContainer}>
        <div className={styles.iconItem}>
          <img src="/assets/vectors/view.svg" alt="View Icon" className={styles.icon} /> {view_count}
        </div>
        <div className={styles.iconItem}>
          <img src="/assets/vectors/heart.svg" alt="Heart Icon" className={styles.icon} /> {view_count}
        </div>
        <div className={styles.iconItem}>
          <img src="/assets/vectors/geo.svg" alt="Geo Icon" className={styles.icon} /> {view_count}
        </div>
      </div>
      <div className={styles.action}>
        <button className={styles.editButton} style={{cursor: 'not-allowed'}}>
          <img src="/assets/vectors/bin.svg" alt="Delete Symbol" />
        </button>
        <button className={styles.editButton}>
          <Link href="/dashboard/admin/properties/edit/slug"> <img src="/assets/vectors/purpedit.svg" alt="Edit Symbol" /> </Link>
        </button>
      </div>
    </div>
  );
}
