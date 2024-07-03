import WeAreOnAMission from '@/components/common/about-page/we-are-on-a-mission';
import LetsFindTheRightOption from '@/components/common/landing-page/lets-find-the-right-option/lets-find-the-right-option';
import NeedHelp from '@/components/common/landing-page/need-help/need-help';
import styles from './about.module.scss';

export default function AboutPage() {
  return (
    <div className={styles.mainContainer}>
      <div className={styles.container}>

        <div className={styles.banner}>
          <p>ABOUT US</p>
        </div>

        <WeAreOnAMission />
        <LetsFindTheRightOption />
        <NeedHelp />
      </div>
    </div>
  )
}
