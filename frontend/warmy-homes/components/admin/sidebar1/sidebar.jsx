import styles from './sidebar.module.scss'
import { MdOutlineDashboard } from "react-icons/md";
import { RiAdvertisementLine } from "react-icons/ri";
import { MdOutlineCategory } from "react-icons/md";

export default function Sidebar1() {

  return (
    <div className={styles.container}>
      <div className={styles.avatarContainer}>
        <div className={styles.avatarInnerContainer}>
        <MdOutlineDashboard className={styles.dashboardIcon} alt="Dashboard" />
        </div>



        <MdOutlineDashboard />
        <RiAdvertisementLine />
        <MdOutlineCategory />


       
      
      </div>
      <div className={styles.linksContainer}> {/* Sidebar Links*/} </div>


      <div className={styles.lougoutContainer}>
        {/* Logout Button*/} </div>



    </div>
  )
}
