import Image from "next/image";
import styles from "./dashboard.module.scss";
import Link from "next/link";

export default function Dashboard() {

  const baseUrl = "/dashboard/admin"; // Define a base URL or dynamic URL (e.g., `/admin`, `/user`, etc.)


  const dashboardCards = [
    { title: "Customers", link: `${baseUrl}/users`, number: "4219", icon: "/assets/vectors/customer-icon.svg" },
    { title: "Categories", link: `${baseUrl}/categories`, number: "5", icon: "/assets/vectors/category-icon.svg" },
    { title: "Adverts", link: `${baseUrl}/properties`, number: "170", icon: "/assets/vectors/advert-icon.svg" },
    { title: "Advert Types", link: `${baseUrl}/adverts-types`, number: "2", icon: "/assets/vectors/advert-type-icon.svg" },
    { title: "Tour Requests", link: `${baseUrl}/tour-requests`, number: "96", icon: "/assets/vectors/tour-request-icon.svg" }
  ]


  return (
    <div className={styles.mainContainer}>
      <div className={styles.container}>
        {dashboardCards.map((card, index) => (
          <Link href={card.link} className={styles.cardLink} key={index}>
            <div className={styles.card}>
              <div>
                <div className={styles.title}> <span className={styles.square}></span> <p>{card.title}</p> </div>
                <h2>{card.number}</h2>
              </div>
              <Image src={card.icon} alt={`${card.title} icon`} className={styles.cardIcon} width={120} height={120} />
            </div>
          </Link>

        ))}
      </div>
    </div>
  )
}