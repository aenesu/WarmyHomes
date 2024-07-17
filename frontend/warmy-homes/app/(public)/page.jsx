import ExplorePropertiesByType from "@/components/common/landing-page/explore-properties-by-type/explore-properties-by-type";
import styles from "./page.module.scss";

import SearchGroup from "@/components/common/landing-page/search-group/search-group";
import ExplorePropertiesByCity from "@/components/common/landing-page/explore-properties-by-city/explore-properties-by-city";
import NeedHelp from "@/components/common/landing-page/need-help/need-help";
import LetsFindTheRightOption from "@/components/common/landing-page/lets-find-the-right-option/lets-find-the-right-option";
import DiscoverPopularProperities from "@/components/common/landing-page/discover-popular-properties/discover-popular-properties";
import GetYourDreamHouse from "@/components/common/landing-page/get-your-dream-house/get-your-dream-house";


export default function HomePage() {

  return (
    <div className={styles.landingPage}>

      <SearchGroup />

      <ExplorePropertiesByType />

      <ExplorePropertiesByCity />

      <GetYourDreamHouse />

      <DiscoverPopularProperities />

      <LetsFindTheRightOption />

      <NeedHelp />

    </div>
  )
}
