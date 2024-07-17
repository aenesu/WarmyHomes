import styles from "./discover-popular-properties.module.scss";

import AdvertCard from "../../advert-card/advert-card";
import Link from "next/link";

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
          <Link key={id} href="/properties/new-york-home">< AdvertCard key={id} {...{ title, city, district, price }} /></Link>
        ))}
      </div>
    </div>
  )
}
