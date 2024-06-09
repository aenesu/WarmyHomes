import styles from "./discover-popular-properties.module.scss";

import AdvertCard from "../../advert-card/advert-card";

export default function DiscoverPopularProperties() {

  const adverts = [
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
  ];

  return (
    <div className={styles.discoverPopularContainer}>
      <div className={styles.exploreTitle}>Discover Popular Properties</div>
      <div className={styles.exploreText}>Featured properties</div>
      <div className={styles.categoryCards}>
        {adverts.map(({ id, title, city, district, price }) => (
          < AdvertCard key={id} {...{ title, city, district, price }} />
        ))}
      </div>
    </div>
  )
}
