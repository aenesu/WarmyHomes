import styles from "./explore-properties-by-type.module.scss"

import ProperityTypeCard from "./properity-type-card/properity-type-card"

export default function ExplorePropertiesByType() {

    const categories = [
        { id: 1, title: "Houses", icon: "/assets/images/categories/house.svg", count: 33, link:"#" },
        { id: 2, title: "Apartments", icon: "/assets/images/categories/apartment.svg", count: 21, link:"#" },
        { id: 3, title: "Offices", icon: "/assets/images/categories/office.svg", count: 85, link:"#" },
        { id: 4, title: "Villas", icon: "/assets/images/categories/villas.svg", count: 15, link:"#" },
        { id: 5, title: "Bungalows", icon: "/assets/images/categories/bungalow.svg", count: 21, link:"#" },
    ]


    return (
        <div className={styles.exploreByTypeContainer}>
            <div className={styles.exploreTitle}>Explore Properties</div>
            <div className={styles.exploreText}>By Types</div>
            <div className={styles.categoryCards}>
                {categories.map(({ id, title, icon, count, link }) => (
                    <ProperityTypeCard key={id} {...{ title, icon, count, link }} />
                ))}
            </div>
        </div>
    )
}
