import styles from "./my-favorites-card.module.scss";

export default function MyFavoritesCard({
  index,
  title,
  country_id,
  city_id,
  district_id,
  category_id,
  advert_type_id,
  price,
  time_id 
}) {
  return (
    <div className={styles.card}>
      <div className={styles.container}>
        <img
          src="/assets/images/house-isolated-field 6.png"
          alt={title}
          className={styles.image}
        />
        <div>
          <div className={styles.title}>{title}</div>
          <div className={styles.location}>{district_id}, {city_id}, {country_id}</div>
          <div className={styles.price}>$ {price.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</div>
        </div>
      </div>
      <div className={styles.category}>{category_id}</div>
      <div className={styles.type}>{advert_type_id}</div>
      {time_id && <div className={styles.time}>{time_id}</div>}
      <div className={styles.action}>
        <button className={styles.actButton}><img src="/assets/vectors/bin.svg" alt="Rubbish Symbol"></img></button> 
      </div>
    </div>
  );
}
