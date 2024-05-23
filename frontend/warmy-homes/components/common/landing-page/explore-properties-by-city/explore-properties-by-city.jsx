import styles from "./explore-properties-by-city.module.scss";

import PropertyCityCard from "./property-city-card/property-city-card";

export default function ExplorePropertiesByCity() {
  const properties = [
    { id: 1, city: "Istanbul", amount: 820 },
    { id: 2, city: "Izmir", amount: 320 },
    { id: 3, city: "Antalya", amount: 80 },
    { id: 4, city: "Bursa", amount: 78 },
    { id: 5, city: "Ankara", amount: 410 },
  ];

  return (
    <div className={styles.exploreByCityContainer}>
      <div className={styles.exploreTitle}>Explore Properties</div>
      <div className={styles.exploreText}>By City</div>
      <div className={styles.cards}>
        {properties.map(({ id, city, amount }) => (
          <PropertyCityCard key={id} {...{ city, amount }} />
        ))}
      </div>
    </div>
  );
}
