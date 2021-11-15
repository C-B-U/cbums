import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournal/study_work.module.css";
import DetailedStudyTitle from "./DetailedStudyTitle";
import DetailedStudyButtonList from "./DetailedStudyButtonList";
class DetailedStudyPage extends PureComponent {
    render() {
        return (
            <React.Fragment>
                <main>
                    <div className={style.main_wrap}>
                        <DetailedStudyTitle studytitle="스터디명" />
                        <DetailedStudyButtonList />
                     </div>
                </main>
            </React.Fragment>
        );
    }
}

export default DetailedStudyPage;
