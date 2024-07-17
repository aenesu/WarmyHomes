import WeAreOnAMission from '@/components/common/about-page/we-are-on-a-mission';
import LetsFindTheRightOption from '@/components/common/landing-page/lets-find-the-right-option/lets-find-the-right-option';
import NeedHelp from '@/components/common/landing-page/need-help/need-help';
import Banner from '@/components/common/banner/banner';
import styles from './about.module.scss';

export default function AboutPage() {
  return (
    <div className={styles.mainContainer}>
      <div className={styles.container}>

        <Banner title="ABOUT US" />
        <WeAreOnAMission />
        <LetsFindTheRightOption />
        <NeedHelp />
      </div>
    </div>
  )
}
