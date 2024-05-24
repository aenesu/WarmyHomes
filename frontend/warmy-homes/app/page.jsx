import ExplorePropertiesByType from "@/components/common/landing-page/explore-properities-by-type/explore-properties-by-type";
import styles from "./page.module.scss";

import SearchGroup from "@/components/common/landing-page/search-group/search-group";
import ExplorePropertiesByCity from "@/components/common/landing-page/explore-properties-by-city/explore-properties-by-city";
import NeedHelp from "@/components/common/landing-page/need-help/need-help";
import LetsFindTheRightOption from "@/components/common/landing-page/lets-find-the-right-option/lets-find-the-right-option";


export default function HomePage() {

  return (
    <div className={styles.landingPage}>

      <SearchGroup />

      <ExplorePropertiesByType />

      <ExplorePropertiesByCity />

      {/* GET YOUR DREAM HOUSE BANNER component */}

      {/* DISCOVER POPULAR PROPETIES component */}

      <LetsFindTheRightOption />

      <NeedHelp />

    </div>
  )
}
