import React from "react";
import styles from "./get-your-dream-house.module.scss";
import RegisterNowButton from "./register-now-button/register-now-button";

export default function GetYourDreamHouse() {
    return (
        <div className={styles.subBanner} style={{ gap: "100px" }}>
            <div>
                <div className={styles.motto}>Get your dream house</div>

                <div style={{ marginTop: "-10px" }}>
                    <p style={{ maxWidth: "500px" }}>
                        Turn your aspirations into reality with 'Get Your Dream House' –
                        where your perfect home becomes a possibility.
                    </p>
                </div>

                <div style={{ marginTop: "30px" }}>
                    <RegisterNowButton />
                </div>
            </div>


        </div>
    );
}
