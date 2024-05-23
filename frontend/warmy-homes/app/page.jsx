import ExplorePropertiesByType from "@/components/common/landing-page/explore-properities-by-type/explore-properties-by-type";
import styles from "./page.module.scss";

import SearchGroup from "@/components/common/landing-page/search-group/search-group";
import ExplorePropertiesByCity from "@/components/common/landing-page/explore-properties-by-city/explore-properties-by-city";


export default function HomePage() {

  return (
    <div className={styles.landingPage}>

      <SearchGroup />

      <ExplorePropertiesByType />

      <ExplorePropertiesByCity />

    </div>
  )
}
