import React, { PureComponent } from "react";
import style from "../../../css/study/study_recruit/study_recruit.module.css";
import RecruitStudyContainer from "./RecruitStudyContainer";
import RecruitStudyFooter from "./RecruitStudyFooter";
import RecruitStudyTitle from "./RecruitStudyTitle";
class RecruitStudyPage extends PureComponent {
    render() {
        return (
            <React.Fragment>
            <main>
                <div className={style.main_wrap}>
                        <RecruitStudyTitle />
                        <RecruitStudyContainer />
                        <RecruitStudyFooter />
                </div>
            </main>
            </React.Fragment>
        );
    }
}

export default RecruitStudyPage;
