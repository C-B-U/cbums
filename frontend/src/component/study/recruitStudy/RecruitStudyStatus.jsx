import React, { PureComponent } from "react";
import style from "../../../css/study/study_recruit/study_recruit.module.css";
class RecruitStudyStatus extends PureComponent {
    render() {
        if (this.props.recruit === true) {
            return (
                <span className={`${style.post_status_unrecruited} ${style.post_status}`}>
                    모집중
                </span>
            )
        }
        else {
             return (
               <span
                 className={`${style.post_status_recruited} ${style.post_status}`}
               >
                 모집완료
               </span>
             );
        }
          
    }
}

export default RecruitStudyStatus;
