import styles from "./explore-properties-by-city.module.scss";

import PropertyCityCard from "./property-city-card/property-city-card";

export default function ExplorePropertiesByCity() {
  const properties = [
    { id: 1, city: "Istanbul", amount: 820, link: "/properties?location=İstanbul" },
    { id: 2, city: "Izmir", amount: 320, link: "/properties?location=İzmir" },
    { id: 3, city: "Antalya", amount: 80, link: "/properties?location=Antalya" },
    { id: 4, city: "Bursa", amount: 78, link: "/properties?location=Bursa" },
    { id: 5, city: "Ankara", amount: 410, link: "/properties?location=Ankara" },
  ];

  return (
    <div className={styles.exploreByCityContainer}>
      <div className={styles.exploreTitle}>Explore Properties</div>
      <div className={styles.exploreText}>By City</div>
      <div className={styles.cards}>
        {properties.map(({ id, city, amount, link }) => (
          <PropertyCityCard key={id} {...{ city, amount, link }} />
        ))}
      </div>
    </div>
  );
}
