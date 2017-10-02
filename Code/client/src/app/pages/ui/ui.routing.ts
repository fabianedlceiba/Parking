import { Routes, RouterModule } from '@angular/router';

import { Ui } from './ui.component';
import { Buttons } from './components/buttons/buttons.component';
import { Grid } from './components/grid/grid.component';
import { Modals } from './components/modals/modals.component';
import { Typography } from './components/typography/typography.component';
import { SlimComponent } from './components/slim/slim.component';

// noinspection TypeScriptValidateTypes
const routes: Routes = [
  {
    path: '',
    component: Ui,
    children: [
      { path: 'buttons', component: Buttons },
      { path: 'grid', component: Grid },
      { path: 'typography', component: Typography },
      { path: 'modals', component: Modals },
      { path: 'slim', component: SlimComponent },
    ],
  },
];

export const routing = RouterModule.forChild(routes);