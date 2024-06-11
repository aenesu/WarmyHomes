import styles from "./we-are-on-a-mission.module.scss";

export default function WeAreOnAMission() {
    return (
        <div className={styles.container}>

            <div className={styles.imageContainer}><img src="/assets/images/still-life-home-decor-cozy-home 1.png" alt="" /></div>

            <div className={styles.textContainer}>
                <div className={styles.title}>We're on a Mission to Change <br /> View of Real Estate Field.</div>
                <p>At the heart of our vision lies a resolute commitment to transform the landscape of the real estate industry. We're not just a company; we're on a mission to change the very essence of how real estate is perceived and experienced. Our journey is defined by innovation, transparency, and a relentless pursuit of excellence. With a bold and forward-thinking approach, we seek to revolutionize the traditional norms of the real estate field, making it more accessible, efficient, and customer-centric. Our aspiration is to shape a future where buying, selling, or investing in real estate is a seamless and empowering experience for all. Join us on this transformative journey as we rewrite the narrative of real estate.</p>
                <div className={styles.iconGroup}>
                    <div className={styles.icon}>
                        <div className={styles.circle}></div>
                        <div className={styles.circleText}>Modern Architect</div>
                    </div>
                    <div className={styles.icon}>
                        <div className={styles.circle}></div>
                        <div className={styles.circleText}>Green Building</div>
                    </div>
                </div>
            </div>
        </div>
    )
}
