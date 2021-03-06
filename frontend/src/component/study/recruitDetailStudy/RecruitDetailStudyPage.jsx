import React, { PureComponent } from 'react';
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";
import RecruitDetailStudyButton from './RecruitDetailStudyButton';
import RecruitDetailStudyComment from './RecruitDetailStudyComment';
import RecruitDetailStudyContents from './RecruitDetailStudyContents';
import RecruitDetailStudyHeadCount from './RecruitDetailStudyHeadCount';
import RecruitDetailStudyPlan from './RecruitDetailStudyPlan';
import RecruitDetailStudyTag from "./RecruitDetailStudyTag";
import RecruitDetailStudyTitle from './RecruitDetailStudyTitle';
import RecruitDetailStudyWriter from './RecruitDetailStudyWriter';

class RecruitDetailStudyPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div className={style.main_wrap}>
            <div className={style.main__studyComplete}>
              <div className={style["main__studyComplete-wrap"]}>
                <RecruitDetailStudyTitle />
                <RecruitDetailStudyWriter />
                <RecruitDetailStudyTag />
                <RecruitDetailStudyContents />
                <RecruitDetailStudyPlan />
                <RecruitDetailStudyHeadCount />
                <RecruitDetailStudyButton />
                <RecruitDetailStudyComment />
              </div>
            </div>
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default RecruitDetailStudyPage;


