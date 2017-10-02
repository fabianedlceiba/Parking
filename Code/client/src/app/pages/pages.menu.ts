export const PAGES_MENU = [
  {
    path: 'pages',
    children: [
      {
        path: 'dashboard',
        data: {
          menu: {
            title: 'general.menu.dashboard',
            icon: 'fa fa-tachometer',
            selected: false,
            expanded: false,
            order: 0,
          },
        },
      },
      {
        path: 'ui',
        data: {
          menu: {
            title: 'general.menu.ui_features',
            icon: 'ion-android-laptop',
            selected: false,
            expanded: false,
            order: 300,
          },
        },
        children: [
          {
            path: 'typography',
            data: {
              menu: {
                title: 'general.menu.typography',
              },
            },
          },
          {
            path: 'buttons',
            data: {
              menu: {
                title: 'general.menu.buttons',
              },
            },
          },
          {
            path: 'icons',
            data: {
              menu: {
                title: 'general.menu.icons',
              },
            },
          },
          {
            path: 'modals',
            data: {
              menu: {
                title: 'general.menu.modals',
              },
            },
          },
          {
            path: 'slim',
            data: {
              menu: {
                title: 'Slim loading bar',
              },
            },
          },
          {
            path: 'grid',
            data: {
              menu: {
                title: 'general.menu.grid',
              },
            },
          },
        ],
      },
      {
        path: 'forms',
        data: {
          menu: {
            title: 'general.menu.form_elements',
            icon: 'ion-compose',
            selected: false,
            expanded: false,
            order: 400,
          },
        },
        children: [
          {
            path: 'inputs',
            data: {
              menu: {
                title: 'general.menu.form_inputs',
              },
            },
          },
          {
            path: 'layouts',
            data: {
              menu: {
                title: 'general.menu.form_layouts',
              },
            },
          },
        ],
      },
      {
        path: '',
        data: {
          menu: {
            title: 'general.menu.pages',
            icon: 'ion-document',
            selected: false,
            expanded: false,
            order: 650,
          },
        },
        children: [
          {
            path: ['/register'],
            data: {
              menu: {
                title: 'general.menu.register',
              },
            },
          },
        ],
      },
      {
        path: '',
        data: {
          menu: {
            title: 'general.menu.menu_level_1',
            icon: 'ion-ios-more',
            selected: false,
            expanded: false,
            order: 700,
          },
        },
        children: [
          {
            path: '',
            data: {
              menu: {
                title: 'general.menu.menu_level_1_1',
                url: '#',
              },
            },
          },
          {
            path: '',
            data: {
              menu: {
                title: 'general.menu.menu_level_1_2',
                url: '#',
              },
            },
            children: [
              {
                path: '',
                data: {
                  menu: {
                    title: 'general.menu.menu_level_1_2_1',
                    url: '#',
                  },
                },
              },
            ],
          },
        ],
      },
      {
        path: '',
        data: {
          menu: {
            title: 'general.menu.external_link',
            url: 'http://akveo.com',
            icon: 'ion-android-exit',
            order: 800,
            target: '_blank',
          },
        },
      },
    ],
  },
];
